package com.twinkle.cloud.common.data;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/14/18 5:12 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
public class GeneralContentResult<T> extends GeneralResult {
    private T resultContent;
    public GeneralContentResult() {
        super();
    }
    public GeneralContentResult(String _resultCode) {
        super(_resultCode);
    }
    public GeneralContentResult(String _resultCode, T _content) {
        super(_resultCode);
        this.resultContent = _content;
    }
    public GeneralContentResult(String _resultCode, T _content, String _description) {
        super(_resultCode, _description);
        this.resultContent = _content;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
