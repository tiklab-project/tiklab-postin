package net.tiklab.postin.apidef.http.test.httpcase.service;

import net.tiklab.postin.apidef.http.test.httpcase.dao.BinaryParamCaseDao;
import net.tiklab.postin.apidef.http.test.httpcase.entity.BinaryParamCaseEntity;
import net.tiklab.postin.apidef.http.test.httpcase.model.BinaryParamCase;
import net.tiklab.postin.apidef.http.test.httpcase.model.BinaryParamCaseQuery;

import net.tiklab.core.exception.ApplicationException;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.beans.BeanMapper;
import net.tiklab.join.JoinTemplate;
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
* BinaryParamCaseServiceImpl
*/
@Service
public class BinaryParamCaseServiceImpl implements BinaryParamCaseService {

    @Autowired
    BinaryParamCaseDao binaryParamCaseDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createBinaryParamCase(@NotNull @Valid BinaryParamCase binaryParamCase) {
        BinaryParamCaseEntity binaryParamCaseEntity = BeanMapper.map(binaryParamCase, BinaryParamCaseEntity.class);

        return binaryParamCaseDao.createBinaryParamCase(binaryParamCaseEntity);
    }

    @Override
    public void updateBinaryParamCase(@NotNull @Valid BinaryParamCase binaryParamCase) {
        BinaryParamCaseEntity binaryParamCaseEntity = BeanMapper.map(binaryParamCase, BinaryParamCaseEntity.class);

        binaryParamCaseDao.updateBinaryParamCase(binaryParamCaseEntity);
    }

    @Override
    public void deleteBinaryParamCase(@NotNull String id) {
        binaryParamCaseDao.deleteBinaryParamCase(id);
    }

    @Override
    public BinaryParamCase findOne(String id) {
        BinaryParamCaseEntity binaryParamCaseEntity = binaryParamCaseDao.findBinaryParamCase(id);

        BinaryParamCase binaryParamCase = BeanMapper.map(binaryParamCaseEntity, BinaryParamCase.class);
        return binaryParamCase;
    }

    @Override
    public List<BinaryParamCase> findList(List<String> idList) {
        List<BinaryParamCaseEntity> binaryParamCaseEntityList =  binaryParamCaseDao.findBinaryParamCaseList(idList);

        List<BinaryParamCase> binaryParamCaseList =  BeanMapper.mapList(binaryParamCaseEntityList,BinaryParamCase.class);
        return binaryParamCaseList;
    }

    @Override
    public BinaryParamCase findBinaryParamCase(@NotNull String id) {
        BinaryParamCase binaryParamCase = findOne(id);

        joinTemplate.joinQuery(binaryParamCase);

        return binaryParamCase;
    }

    @Override
    public List<BinaryParamCase> findAllBinaryParamCase() {
        List<BinaryParamCaseEntity> binaryParamCaseEntityList =  binaryParamCaseDao.findAllBinaryParamCase();

        List<BinaryParamCase> binaryParamCaseList =  BeanMapper.mapList(binaryParamCaseEntityList,BinaryParamCase.class);

        joinTemplate.joinQuery(binaryParamCaseList);

        return binaryParamCaseList;
    }

    @Override
    public List<BinaryParamCase> findBinaryParamCaseList(BinaryParamCaseQuery binaryParamCaseQuery) {
        List<BinaryParamCaseEntity> binaryParamCaseEntityList = binaryParamCaseDao.findBinaryParamCaseList(binaryParamCaseQuery);

        List<BinaryParamCase> binaryParamCaseList = BeanMapper.mapList(binaryParamCaseEntityList,BinaryParamCase.class);

        joinTemplate.joinQuery(binaryParamCaseList);

        return binaryParamCaseList;
    }

    @Override
    public Pagination<BinaryParamCase> findBinaryParamCasePage(BinaryParamCaseQuery binaryParamCaseQuery) {
        Pagination<BinaryParamCaseEntity>  pagination = binaryParamCaseDao.findBinaryParamCasePage(binaryParamCaseQuery);

        List<BinaryParamCase> binaryParamCaseList = BeanMapper.mapList(pagination.getDataList(),BinaryParamCase.class);

        joinTemplate.joinQuery(binaryParamCaseList);

        return PaginationBuilder.build(pagination,binaryParamCaseList);
    }

    @Override
    public String  findBinaryParamCaseByte(BinaryParamCaseQuery binaryParamCaseQuery) {
        List<BinaryParamCase> binaryParamCaseList = findBinaryParamCaseList(binaryParamCaseQuery);
        if (CollectionUtils.isNotEmpty(binaryParamCaseList)){
            String fileName = binaryParamCaseList.get(0).getFileName();
            try {
                String path="http://192.168.2.6:8070/file/"+fileName;
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