package com.twinkle.cloud.common.data;

import lombok.Data;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/14/18 5:15 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
public class PageResult {
    /**
     * Required. currentPage:the No. of current page.
     */
    private Integer currentPage;
    /**
     * Required. totalPage: Total count of the pages.
     */
    private Integer totalPage;
    /**
     * Required. pageSize: the size of the page.
     */
    private Integer pageSize;
    /**
     * Optional. totalRecords: total records of the entities.
     */
    private Long totalRecords;
}
