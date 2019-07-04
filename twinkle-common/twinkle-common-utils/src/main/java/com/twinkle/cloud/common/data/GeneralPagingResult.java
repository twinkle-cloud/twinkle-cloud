package com.twinkle.cloud.common.data;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/14/18 5:14 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
public class GeneralPagingResult<T> extends GeneralResult {
    private T resultContent;
    private PageResult pageResult;
    public GeneralPagingResult(){
        super();
    }
    public GeneralPagingResult(String _resultCode){
        super(_resultCode);
    }
    public GeneralPagingResult(String _resultCode, String _description){
        super(_resultCode, _description);
    }

    public GeneralPagingResult(String _resultCode, T _content){
        super(_resultCode);
        this.resultContent = _content;
    }

    public GeneralPagingResult(String _resultCode, T _content, PageResult _pageResult){
        super(_resultCode);
        this.resultContent = _content;
        this.pageResult = _pageResult;
    }

    public GeneralPagingResult(String _resultCode, T _content, String _description){
        super(_resultCode, _description);
        this.resultContent = _content;
    }

    public GeneralPagingResult(String _resultCode, T _content, PageResult _pageResult, String _description){
        super(_resultCode, _description);
        this.resultContent = _content;
        this.pageResult = _pageResult;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
