package io.pirkka.RestDemo;

import org.hibernate.dialect.*;

import java.sql.Types;

/**
 * Wrap default PostgreSQL9Dialect with 'json' type.
 *
 * @author timfulmer
 */
public class JsonPostgreSqlDialect extends PostgreSQL94Dialect {

    public JsonPostgreSqlDialect() {
        super();
        this.registerColumnType(Types.JAVA_OBJECT, "json");
    }
}