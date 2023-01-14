package jpabasic.hellojpa.dialect;

import org.hibernate.dialect.MySQL8Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class MyMySQLDialect extends MySQL8Dialect {
    public MyMySQLDialect() {
        registerFunction("사용자_정의_함수_이름", new StandardSQLFunction("사용자_정의_함수_이름", StandardBasicTypes.STRING));
    }
}
