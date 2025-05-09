package io.tiklab.postin.review.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.review.model.Review;
import io.tiklab.postin.review.model.ReviewQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 评审 服务接口
*/
@JoinProvider(model = Review.class)
public interface ReviewService {

    /**
    * 创建评审
    * @param review
    * @return
    */
    String createReview(@NotNull @Valid Review review);

    /**
    * 更新评审
    * @param review
    */
    void updateReview(@NotNull @Valid Review review);

    /**
    * 删除评审
    * @param id
    */
    void deleteReview(@NotNull String id);

    @FindOne
    Review findOne(@NotNull String id);

    @FindList
    List<Review> findList(List<String> idList);

    /**
    * 根据id查找评审
    * @param id
    * @return
    */
    Review findReview(@NotNull String id);

    /**
    * 查找所有评审
    * @return
    */
    @FindAll
    List<Review> findAllReview();

    /**
    * 根据查询参数查询评审列表
    * @param reviewQuery
    * @return
    */
    List<Review> findReviewList(ReviewQuery reviewQuery);

    /**
    * 根据查询参数按分页查询评审
    * @param reviewQuery
    * @return
    */
    Pagination<Review> findReviewPage(ReviewQuery reviewQuery);

}