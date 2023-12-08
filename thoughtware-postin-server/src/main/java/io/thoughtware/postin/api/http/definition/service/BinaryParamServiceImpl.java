package io.thoughtware.postin.api.http.definition.service;

import io.thoughtware.postin.api.http.definition.dao.BinaryParamDao;
import io.thoughtware.postin.api.http.definition.entity.BinaryParamEntity;
import io.thoughtware.postin.api.http.definition.model.BinaryParam;
import io.thoughtware.postin.api.http.definition.model.BinaryParamQuery;

import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.beans.BeanMapper;
import io.thoughtware.join.JoinTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * 定义
 * http协议
 * binary服务
*/
@Service
public class BinaryParamServiceImpl implements BinaryParamService {

    @Autowired
    BinaryParamDao binaryParamDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createBinaryParam(@NotNull @Valid BinaryParam binaryParam) {
        BinaryParamEntity binaryParamEntity = BeanMapper.map(binaryParam, BinaryParamEntity.class);

        return binaryParamDao.createBinaryParam(binaryParamEntity);
    }

    @Override
    public void updateBinaryParam(@NotNull @Valid BinaryParam binaryParam) {
        BinaryParamEntity binaryParamEntity = BeanMapper.map(binaryParam, BinaryParamEntity.class);

        binaryParamDao.updateBinaryParam(binaryParamEntity);
    }

    @Override
    public void deleteBinaryParam(@NotNull String id) {
        binaryParamDao.deleteBinaryParam(id);
    }

    @Override
    public BinaryParam findOne(String id) {
        BinaryParamEntity binaryParamEntity = binaryParamDao.findBinaryParam(id);

        BinaryParam binaryParam = BeanMapper.map(binaryParamEntity, BinaryParam.class);
        return binaryParam;
    }

    @Override
    public List<BinaryParam> findList(List<String> idList) {
        List<BinaryParamEntity> binaryParamEntityList =  binaryParamDao.findBinaryParamList(idList);

        List<BinaryParam> binaryParamList =  BeanMapper.mapList(binaryParamEntityList,BinaryParam.class);
        return binaryParamList;
    }

    @Override
    public BinaryParam findBinaryParam(@NotNull String id) {
        BinaryParam binaryParam = findOne(id);

        joinTemplate.joinQuery(binaryParam);

        return binaryParam;
    }

    @Override
    public List<BinaryParam> findAllBinaryParam() {
        List<BinaryParamEntity> binaryParamEntityList =  binaryParamDao.findAllBinaryParam();

        List<BinaryParam> binaryParamList =  BeanMapper.mapList(binaryParamEntityList,BinaryParam.class);

        joinTemplate.joinQuery(binaryParamList);

        return binaryParamList;
    }

    @Override
    public List<BinaryParam> findBinaryParamList(BinaryParamQuery binaryParamQuery) {
        List<BinaryParamEntity> binaryParamEntityList = binaryParamDao.findBinaryParamList(binaryParamQuery);

        List<BinaryParam> binaryParamList = BeanMapper.mapList(binaryParamEntityList,BinaryParam.class);

        joinTemplate.joinQuery(binaryParamList);

        return binaryParamList;
    }

    @Override
    public Pagination<BinaryParam> findBinaryParamPage(BinaryParamQuery binaryParamQuery) {
        Pagination<BinaryParamEntity>  pagination = binaryParamDao.findBinaryParamPage(binaryParamQuery);

        List<BinaryParam> binaryParamList = BeanMapper.mapList(pagination.getDataList(),BinaryParam.class);

        joinTemplate.joinQuery(binaryParamList);

        return PaginationBuilder.build(pagination,binaryParamList);
    }

    @Override
    public String  findBinaryParamByte(BinaryParamQuery binaryParamQuery) {
        List<BinaryParam> binaryParamList = findBinaryParamList(binaryParamQuery);
        if (CollectionUtils.isNotEmpty(binaryParamList)){
            String fileName = binaryParamList.get(0).getFileName();
            try {
                String path="http://192.168.2.5:8070/file/"+fileName;
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();// 利用HttpURLConnection对象,我们可以从网络中获取网页数据.
                conn.setDoInput(true);
                conn.connect();
                InputStream is = conn.getInputStream(); // 得到网络返回的输入流
                InputStreamReader isr = new InputStreamReader(is, "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String s = null;
                StringBuffer sb= new StringBuffer();
                while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
                    sb.append(s);
                }
                br.close();
                return sb.toString();
            } catch (Exception e) {
                throw new ApplicationException(e);
            }
        }

//        String allURL = "http://"+ request.getServerName()+request.getServerPort()+"/file/"+path;
        //path为url路径

        return null;
    }


}