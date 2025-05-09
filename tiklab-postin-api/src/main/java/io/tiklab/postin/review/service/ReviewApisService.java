package io.tiklab.postin.review.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.api.apix.model.ApiList;
import io.tiklab.postin.review.model.ReviewApis;
import io.tiklab.postin.review.model.ReviewApisQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 评审关联的接口 服务接口
*/
@JoinProvider(model = ReviewApis.class)
public interface ReviewApisService {

    /**
    * 创建评审关联的接口
    * @param reviewApis
    * @return
    */
    String createReviewApis(@NotNull @Valid ReviewApis reviewApis);


    /**
     * 批量关联接口
     * @param reviewApisList
     */
    void batchCreateReviewApis(List<ReviewApis> reviewApisList);


    /**
    * 更新评审关联的接口
    * @param reviewApis
    */
    void updateReviewApis(@NotNull @Valid ReviewApis reviewApis);

    /**
    * 删除评审关联的接口
    * @param id
    */
    void deleteReviewApis(@NotNull String id);

    @FindOne
    ReviewApis findOne(@NotNull String id);

    @FindList
    List<ReviewApis> findList(List<String> idList);

    /**
    * 根据id查找评审关联的接口
    * @param id
    * @return
    */
    ReviewApis findReviewApis(@NotNull String id);

    /**
    * 查找所有评审关联的接口
    * @return
    */
    @FindAll
    List<ReviewApis> findAllReviewApis();

    /**
    * 根据查询参数查询评审关联的接口列表
    * @param reviewApisQuery
    * @return
    */
    List<ReviewApis> findReviewApisList(ReviewApisQuery reviewApisQuery);

    /**
    * 根据查询参数按分页查询评审关联的接口
    * @param reviewApisQuery
    * @return
    */
    Pagination<ReviewApis> findReviewApisPage(ReviewApisQuery reviewApisQuery);

//    /**
//     * 根据查询参数按分页查询评审已关联的接口
//     * @param reviewApisQuery
//     * @return
//     */
//    Pagination<ReviewLinkedCase> findLinkedCasePage(ReviewApisQuery reviewApisQuery);
//
    /**
     * 根据查询参数按分页查询评审未关联的接口
     * @param reviewApisQuery
     * @return
     */
    Pagination<ApiList> findUnlinkCasePage(ReviewApisQuery reviewApisQuery);

}