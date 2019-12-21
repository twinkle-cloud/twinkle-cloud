package com.twinkle.cloud.common.data;

import com.twinkle.cloud.common.constant.ResultCode;
import com.twinkle.cloud.common.exception.BaseException;
import com.twinkle.cloud.common.exception.ErrorType;
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
public class GeneralResult<T> {
    private String code;
    private T data;
    private String description;
    public GeneralResult() {
    }
    public GeneralResult(String _resultCode) {
        this.code = _resultCode;
    }
    public GeneralResult(String _resultCode, T _data) {
        this.code = _resultCode;
        this.data = _data;
    }
    public GeneralResult(String _resultCode, T _data, String _description) {
        this.code = _resultCode;
        this.data = _data;
        this.description = _description;
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @param _data
     * @return GeneralContentResult
     */
    public static GeneralResult success(Object _data) {
        return new GeneralResult<>(ResultCode.OPERATION_SUCCESS, _data);
    }

    /**
     * 快速创建成功结果
     *
     * @return Result
     */
    public static GeneralResult success() {
        return success(null);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @return Result
     */
    public static GeneralResult fail() {
        return new GeneralResult(ResultCode.OPERATION_FAILED_UNKOWN);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @param _exception
     * @return Result
     */
    public static GeneralResult fail(BaseException _exception) {
        return fail(_exception, null);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param _data
     * @return GeneralContentResult
     */
    public static GeneralResult fail(BaseException _exception, Object _data) {
        return new GeneralResult<>(_exception.getCode(), _data);
    }

    public static GeneralResult fail(ErrorType _type, Object _data) {
        return new GeneralResult<>(_type.getCode(), _data);
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
