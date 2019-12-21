package com.twinkle.cloud.core.usermgmt.config;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.method.HandlerMethod;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import springfox.documentation.service.ObjectVendorExtension;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.swagger.annotations.Annotations;
import springfox.documentation.swagger.common.SwaggerPluginSupport;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-07-23 16:08<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
//@Component
//@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
public class TwinkleOperationBuilder implements OperationBuilderPlugin {
    @Override
    public void apply(OperationContext context) {

//        HandlerMethod handlerMethod = context.getHandlerMethod();
//
//        Optional<ApiOperation> apiOperationOperational = Annotations
//                .findApiOperationAnnotation(handlerMethod.getMethod());
//        String awsApiGatewayBaseUri  = "";//Fetch it from DB/Service
//
//        if (apiOperationOperational.isPresent()) {
//            ApiOperation apiOperation = apiOperationOperational.get();
//            ObjectVendorExtension extension = new ObjectVendorExtension(
//                    ensurePrefixed(AWS_X_AMAZON_APIGATEWAY_INTEGRATION));
//            extension.addProperty(new StringVendorExtension(AWS_X_AMAZON_APIGATEWAY_INTEGRATION_TYPE, "http"));
//            extension.addProperty(new StringVendorExtension(AWS_X_AMAZON_APIGATEWAY_INTEGRATION_HTTPMETHOD, apiOperation.httpMethod()));
//            extension.addProperty(new StringVendorExtension(AWS_X_AMAZON_APIGATEWAY_INTEGRATION_URI, awsApiGatewayBaseUri + getRequestUri(handlerMethod)));
//
//            ObjectVendorExtension requestParameters = new ObjectVendorExtension(AWS_X_AMAZON_APIGATEWAY_INTEGRATION_REQUEST_PARAMS);
//
//            processApiParamAnnotation(requestParameters, handlerMethod);
//            processApiImplicitParamsAnnotation(handlerMethod, requestParameters);
//
//            ObjectVendorExtension awsResponses = new ObjectVendorExtension(AWS_X_AMAZON_APIGATEWAY_INTEGRATION_REQUEST_RESPONSES);
//            processApiResponsesAnnotation( awsResponses,handlerMethod);
//
//            if (requestParameters != null && requestParameters.getValue() != null
//                    && requestParameters.getValue().size() > 0) {
//                extension.addProperty(requestParameters);
//            }
//            if (awsResponses != null && awsResponses.getValue() != null
//                    && awsResponses.getValue().size() > 0) {
//                extension.addProperty(awsResponses);
//            }
//
//            List<VendorExtension> extensions = new ArrayList<VendorExtension>();
//            extensions.add(extension);
//            context.operationBuilder().extensions(extensions);
//
//        }
    }
    private String getRequestUri(HandlerMethod handlerMethod){
        RequestMapping requestMapping = handlerMethod.getMethodAnnotation(RequestMapping.class);
        RequestMapping controllerRequestMapping = handlerMethod.getBeanType().getAnnotation(RequestMapping.class);
        String[] paths = requestMapping.path();
        String[] requestPaths = controllerRequestMapping.value();
        String requestUri = "";
        if (requestPaths != null && requestPaths.length > 0) {
            requestUri = requestPaths[0];
            if (paths != null && paths.length > 0) {
                requestUri = requestUri + paths[0];
            }
        }
        return requestUri;
    }
    private void processApiResponsesAnnotation(ObjectVendorExtension awsResponses,HandlerMethod handlerMethod) {
//        Optional<ApiResponses> apiResponsesOptional = Annotations
//                .findApiResponsesAnnotations(handlerMethod.getMethod());
//        if (apiResponsesOptional.isPresent()) {
//            ApiResponses apiResponses = apiResponsesOptional.get();
//            ApiResponse[] apiResponseArray = apiResponses.value();
//            for (ApiResponse apiResponse : apiResponseArray) {
//                ObjectVendorExtension awsResponse = new ObjectVendorExtension(String.valueOf(apiResponse.code()));
//                awsResponse
//                        .addProperty(new StringVendorExtension("statusCode", String.valueOf(apiResponse.code())));
//                awsResponses.addProperty(awsResponse);
//            }
//
//        }
    }

    private void processApiImplicitParamsAnnotation(HandlerMethod handlerMethod,
                                                    ObjectVendorExtension requestParameters) {
        ApiImplicitParams apiImplicitParams = handlerMethod.getMethodAnnotation(ApiImplicitParams.class);
        if (apiImplicitParams != null) {
            String requestParameterIntegration = "";
            String requestParameterMethod = "";
            for (ApiImplicitParam apiImplicitParam : apiImplicitParams.value()) {
                if (apiImplicitParam.paramType() != null && apiImplicitParam.paramType().equals("path")) {
                    requestParameterIntegration = "integration.request.path." + apiImplicitParam.name();
                    requestParameterMethod = "method.request.path." + apiImplicitParam.name();
                    requestParameters.addProperty(
                            new StringVendorExtension(requestParameterIntegration, requestParameterMethod));
                } else if (apiImplicitParam.paramType() != null && apiImplicitParam.paramType().equals("query")) {
                    requestParameterIntegration = "integration.request.querystring." + apiImplicitParam.name();
                    requestParameterMethod = "method.request.querystring." + apiImplicitParam.name();
                    requestParameters.addProperty(
                            new StringVendorExtension(requestParameterIntegration, requestParameterMethod));
                }
            }
        }
    }

    private void processApiParamAnnotation(ObjectVendorExtension requestParameters, HandlerMethod handlerMethod) {
        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
        for (MethodParameter methodParameter : methodParameters) {
            ApiParam apiParam = methodParameter.getParameterAnnotation(ApiParam.class);
            String requestParameterIntegration = "";
            String requestParameterMethod = "";
            if (apiParam != null) {
                PathVariable pathVariable = methodParameter.getParameterAnnotation(PathVariable.class);
                RequestParam requestParam = methodParameter.getParameterAnnotation(RequestParam.class);
                if (pathVariable != null) {
                    requestParameterIntegration = "integration.request.path." + apiParam.name();
                    requestParameterMethod = "method.request.path." + apiParam.name();
                    requestParameters.addProperty(
                            new StringVendorExtension(requestParameterIntegration, requestParameterMethod));
                } else if (requestParam != null) {
                    requestParameterIntegration = "integration.request.querystring." + apiParam.name();
                    requestParameterMethod = "method.request.querystring." + apiParam.name();
                    requestParameters.addProperty(
                            new StringVendorExtension(requestParameterIntegration, requestParameterMethod));
                }
            }
        }
    }

    private String ensurePrefixed(String name) {
        if (!isNullOrEmpty(name)) {
            if (!name.startsWith("x-")) {
                name = "x-" + name;
            }
        }
        return name;
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }
}
