
create database t_gateway owner t_admin;
GRANT ALL PRIVILEGES ON DATABASE t_gateway to t_admin;
\c t_gateway;
ALTER SCHEMA public OWNER to t_admin;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO t_admin;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO t_admin;
create extension "uuid-ossp";

-- Create the trigger for update the UPDATE_DATE when updating some rows.
CREATE OR REPLACE FUNCTION T_UPDATE_DATE()
RETURNS TRIGGER AS $$
BEGIN
  NEW.UPDATE_DATE = NOW();
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- 网关路由表
DROP TABLE IF EXISTS public.T_GATEWAY_ROUTE;
CREATE TABLE public.T_GATEWAY_ROUTE
(
    ID character (36) primary key,
    ROUTE_ID     VARCHAR(128) NOT NULL,
    URI          VARCHAR(128) NOT NULL,
    PREDICATES   TEXT NOT NULL,
    FILTERS      TEXT,
    ORDERS       INTEGER DEFAULT 1,
    DESCRIPTION  VARCHAR(500),
    STATUS       SMALLINT  DEFAULT 1,
    CREATOR_ID   character (36),
    CREATE_DATE timestamp(0) with time zone DEFAULT now(),
    UPDATE_DATE timestamp(0) with time zone DEFAULT now(),
    UNIQUE(URI)
);

ALTER TABLE public.T_GATEWAY_ROUTE OWNER to t_admin;
COMMENT ON TABLE public.T_GATEWAY_ROUTE IS '网关路由表';
COMMENT ON COLUMN public.T_GATEWAY_ROUTE.ID IS 'ID';
COMMENT ON COLUMN public.T_GATEWAY_ROUTE.ROUTE_ID IS '路由id';
COMMENT ON COLUMN public.T_GATEWAY_ROUTE.URI IS 'uri路径';
COMMENT ON COLUMN public.T_GATEWAY_ROUTE.PREDICATES IS '判定器';
COMMENT ON COLUMN public.T_GATEWAY_ROUTE.FILTERS IS '过滤器';
COMMENT ON COLUMN public.T_GATEWAY_ROUTE.ORDERS IS '排序';
COMMENT ON COLUMN public.T_GATEWAY_ROUTE.DESCRIPTION IS '外键元数据元素的ID';
COMMENT ON COLUMN public.T_GATEWAY_ROUTE.STATUS IS '组件模板类型，参考数据字典：ENTRY_TEMPLATE.TYPE';

-- Enable the trigger to update the UPDATE_DATE automatically.
CREATE TRIGGER T_UD_T_GATEWAY_ROUTE BEFORE UPDATE ON public.T_GATEWAY_ROUTE
FOR EACH ROW
EXECUTE PROCEDURE T_UPDATE_DATE();

-- DML初始数据
-- 路由数据
INSERT INTO T_GATEWAY_ROUTE (id, route_id, uri, predicates, filters, orders, description, status, creator_id)
VALUES
('7e693ad5-26df-11ea-af25-0cc47a669986',
 'authorization-server',
 'lb://twinkle-authorization:8000',
 '[{"name":"Path","args":{"pattern":"/authorization/**"}}]',
 '[{"name":"StripPrefix","args":{"parts":"1"}}]',
 100,
 '授权认证服务网关注册',
 1, 'system'),
('8906e027-26df-11ea-af25-0cc47a669986',
 'authentication-server',
 'lb://twinkle-authentication:8001',
 '[{"name":"Path","args":{"pattern":"/authentication/**"}}]',
 '[{"name":"StripPrefix","args":{"parts":"1"}}]',
 100,
 '签权服务网关注册',
 1, 'system'),
('93494192-26df-11ea-af25-0cc47a669986',
 'twinkle-usermgmt',
 'lb://twinkle-usermgmt:8010',
 '[{"name":"Path","args":{"pattern":"/tenant/**"}}]',
 '[{"name":"StripPrefix","args":{"parts":"1"}}]',
 100,
 '系统管理相关接口',
 1, 'system'),
('9d1f8421-26df-11ea-af25-0cc47a669986',
 'gateway-admin',
 'lb://twinkle-gateway-admin:9999',
 '[{"name":"Path","args":{"pattern":"/gateway-admin/**"}}]',
 '[{"name":"StripPrefix","args":{"parts":"1"}}]',
 100,
 '网关管理相关接口',
 1, 'system');


