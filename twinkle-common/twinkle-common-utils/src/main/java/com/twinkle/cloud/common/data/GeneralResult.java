package com.twinkle.cloud.common.data;

import com.twinkle.cloud.common.constant.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/14/18 5:13 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
public class GeneralResult {
    @NotNull
    private String resultCode = ResultCode.OPERATION_SUCCESS;
    private String detailDescription;
    public GeneralResult(){}
    public GeneralResult(String _resultCode) {
        this.resultCode = _resultCode;
    }
    public GeneralResult(String _resultCode, String _description) {
        this.resultCode = _resultCode;
        this.detailDescription = _description;
    }
    public static GeneralResult returnOk() {
        return new GeneralResult(ResultCode.OPERATION_SUCCESS);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
