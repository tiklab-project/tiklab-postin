#!/bin/sh

DIRS=$(dirname "$PWD")

APP_MAIN="io.thoughtware.postin.PostInApplication"

#jdk
JDK_VERSION=jdk-16.0.2
YAML=${DIRS}/conf/application.yaml
valid_jdk(){
  if [ -d "${DIRS}/embbed/${JDK_VERSION}" ]; then
      #echo "user embbed jdk ${JAVA_HOME}"
      JAVA_HOME="${DIRS}/embbed/${JDK_VERSION}"
  else
      echo "Unable to find embbed jdk!"
      exit 1;
  fi
}

PID=0
getPID(){
    javaps=`$JAVA_HOME/bin/jps -l | grep $APP_MAIN`
    if [ -n "$javaps" ]; then
        PID=`echo $javaps | awk '{print $1}'`
    else
        PID=0
    fi
}

shutdown(){
    getPID
    echo "================================================================================================================"
    if [ $PID -ne 0 ]; then
        echo -n "stopping $APP_MAIN(PID=$PID)..."
        kill -9 $PID

        if [ $? -eq 0 ]; then
            echo "[success]"
        else
            echo "[failed]"
        fi
        kill_pgsql
    else
        echo "$APP_MAIN is not running"
        kill_pgsql
    fi
    echo "================================================================================================================"
}

db_port=0
pg_port(){
    db_port=$(awk -F": *" '/^postgresql:/ {
        inf=1
        next
    }
    inf && /^  db:/ {
        db=1
        next
    }
    db && /^    port:/ {
        print $2
        exit
    }' "${YAML}")

   #echo "PostgreSQL start Port: ${db_port}"
}

db_enable="false"
pg_enable(){
    db_enable=$(awk -F": *" '/^postgresql:/ {
        inf=1
        next
    }
    inf && /^  embbed:/ {
        embbed=1
        next
    }
    embbed && /^    enable:/ {
        print $2
        exit
    }' "${YAML}")

   #echo "PostgreSQL embbed enable: ${db_enable}"
}

kill_pgsql(){
  pg_enable
  pg_port
  if [ "${db_enable}" = "true" ]; then
        if [ "${db_port}" = "0" ]; then
            echo "find pgsql port error "
            exit 1
        fi

        pids=$(netstat -antp | grep "${db_port}" | grep "/postgres" | grep -v "postgres: po"  | awk '{print $7}' | cut -d'/' -f1)
        # shellcheck disable=SC2039
         if [ "${pids}" != "" ]; then
            unique_pids=$(echo "${pids}" | awk '!seen[$0]++')
            echo -n "stopping pgsql (PID=${unique_pids})..."
            # 杀死占用端口的进程
            kill -9 "${unique_pids}"

            pids=$(netstat -antp | grep "${db_port}" | grep "/postgres" | grep -v "postgres: po"  | awk '{print $7}' | cut -d'/' -f1)
            # shellcheck disable=SC2039
            if [ "${pids}" != "" ]; then
                 echo "[failed]"
            else
                echo "[success]"
            fi
        else
            echo "pgsql is not running"
        fi
  fi
}

clean_catch(){
    rm -rf /tmp/.s.PGSQL.${db_port}
    rm -rf /tmp/.s.PGSQL.${db_port}

    data_home=$(awk -F': ' '/DATA_HOME:/ {print $2}' "${YAML}")
    if [ -d "${data_home}" ]; then
        rm -rf ${data_home}/postgresql/postmaster.pid
    fi
}

valid_jdk
shutdown
clean_catch
