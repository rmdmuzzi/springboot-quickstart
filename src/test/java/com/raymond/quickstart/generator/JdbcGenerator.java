package com.raymond.quickstart.generator;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;

/**
 * @author raymondmuzzi
 * @since 2023-10-14 23:11:14
 */
class JdbcGenerator {

    /**
     * keyword
     */
    private static final String IMPORT = "import";
    private static final String PACKAGE = "package";
    private static final String PUBLIC = "public";
    private static final String PRIVATE = "private";
    private static final String CLASS = "class";
    private static final String VOID = "void";
    private static final String RETURN = "return";
    private static final String THIS = "this";

    /**
     * getter & setter
     */
    private static final String GETTER = "get";
    private static final String SETTER = "set";

    /**
     * symbol
     */
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";
    private static final String TAB = "    ";
    private static final String SEMICOLON = ";";
    private static final String OPEN_BRACE = "{";
    private static final String CLOSE_BRACE = "}";
    private static final String OPEN_PARENTHESIS = "(";
    private static final String CLOSE_PARENTHESIS = ")";
    private static final String COLUMN_SPLITTER = "_";
    private static final String DOT_REG = "\\.";
    private static final String DOT = ".";
    private static final String EQUALS = "=";

    /**
     * comment
     */
    private static final String COMMENT_START = "/**";
    private static final String COMMENT_NORMAL = " * ";
    private static final String COMMENT_END = " */";
    private static final String COMMENT_AUTHOR_INFO = "@author system";
    private static final String COMMENT_SINCE_INFO = "@since ";

    private static final String SINCE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static {
        try {
            Class.forName(DataSource.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void generate(String basePackage, String scheme, String tableName) {
        String sql = "select * from " + scheme + DOT + tableName + " limit 1";
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssm_db?serverTimezone=Asia/Shanghai&useSSL=false", "root", "root");
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {
            StringBuilder classContent = new StringBuilder();
            ResultSetMetaData rsMetaData = rs.getMetaData();
            // package row
            packageRow(basePackage, classContent);
            // import row
            importRow(rsMetaData, classContent);
            // comment info
            commentRow(rsMetaData, classContent);
            // class row
            classRow(tableName, rs, classContent);

            System.out.println(classContent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void classRow(String tableName, ResultSet rs, StringBuilder classContent) throws SQLException {
        ResultSetMetaData rsMetaData = rs.getMetaData();

        // class row
        classContent.append(PUBLIC).append(SPACE).append(CLASS).append(SPACE)
                .append(firstLetterToUpper(toCamelCase(tableName))).append(SPACE).append(OPEN_BRACE).append(NEW_LINE);

        // field rows
        int columnCount = rsMetaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            classContent.append(TAB).append(PRIVATE).append(SPACE).append(simpleType(rsMetaData.getColumnClassName(i)))
                    .append(SPACE).append(toCamelCase(rsMetaData.getColumnLabel(i))).append(SEMICOLON).append(NEW_LINE);
        }
        classContent.append(NEW_LINE);

        // getter
        rsMetaData = rs.getMetaData();
        for (int i = 1; i <= columnCount; i++) {
            classContent.append(TAB).append(PUBLIC).append(SPACE).append(simpleType(rsMetaData.getColumnClassName(i)))
                    .append(SPACE).append(GETTER).append(firstLetterToUpper(rsMetaData.getColumnLabel(i))).append(OPEN_PARENTHESIS).append(CLOSE_PARENTHESIS)
                    .append(SPACE).append(OPEN_BRACE).append(NEW_LINE);
            classContent.append(TAB).append(TAB).append(RETURN).append(SPACE).append(toCamelCase(rsMetaData.getColumnLabel(i))).append(SEMICOLON).append(NEW_LINE);
            classContent.append(TAB).append(CLOSE_BRACE).append(NEW_LINE);
            classContent.append(NEW_LINE);
        }

        // setter
        rsMetaData = rs.getMetaData();
        for (int i = 1; i <= columnCount; i++) {
            classContent.append(TAB).append(PUBLIC).append(SPACE).append(VOID)
                    .append(SPACE).append(SETTER).append(firstLetterToUpper(rsMetaData.getColumnLabel(i))).append(OPEN_PARENTHESIS)
                    .append(simpleType(rsMetaData.getColumnClassName(i))).append(SPACE)
                    .append(toCamelCase(rsMetaData.getColumnLabel(i))).append(CLOSE_PARENTHESIS).append(SPACE)
                    .append(OPEN_BRACE).append(NEW_LINE);
            classContent.append(TAB).append(TAB).append(THIS).append(DOT).append(toCamelCase(rsMetaData.getColumnLabel(i)))
                    .append(SPACE).append(EQUALS).append(SPACE).append(toCamelCase(rsMetaData.getColumnLabel(i)))
                    .append(SEMICOLON).append(NEW_LINE);
            classContent.append(TAB).append(CLOSE_BRACE).append(NEW_LINE);
            classContent.append(NEW_LINE);

        }
        classContent.append(CLOSE_BRACE).append(NEW_LINE);
    }

    private void commentRow(ResultSetMetaData rsMetaData,
                            StringBuilder classContent) throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat(SINCE_FORMAT);
        classContent.append(COMMENT_START).append(NEW_LINE);
        classContent.append(COMMENT_NORMAL).append(firstLetterToUpper(toCamelCase(rsMetaData.getTableName(1)))).append(NEW_LINE);
        classContent.append(COMMENT_NORMAL).append(NEW_LINE);
        classContent.append(COMMENT_NORMAL).append(COMMENT_AUTHOR_INFO).append(NEW_LINE);
        classContent.append(COMMENT_NORMAL).append(COMMENT_SINCE_INFO).append(sdf.format(new Date())).append(NEW_LINE);
        classContent.append(COMMENT_END).append(NEW_LINE);
    }

    private void importRow(ResultSetMetaData rsMetaData,
                           StringBuilder classContent) throws SQLException {

        LinkedHashSet<String> importSet = new LinkedHashSet<>();
        int columnCount = rsMetaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String columnClassName = rsMetaData.getColumnClassName(i);
            importSet.add(columnClassName);
        }

        for (String importClass : importSet) {
            classContent.append(IMPORT).append(SPACE).append(importClass).append(SEMICOLON).append(NEW_LINE);
        }
        classContent.append(NEW_LINE);
    }

    private void packageRow(String basePackage, StringBuilder classContent) {
        classContent.append(PACKAGE).append(SPACE).append(basePackage).append(SEMICOLON).append(NEW_LINE);
        classContent.append(NEW_LINE);
    }

    private String toCamelCase(String column) {
        if (StringUtils.isBlank(column)) {
            throw new RuntimeException("Column name is empty!");
        }
        column = column.toLowerCase();
        String[] words = column.split(COLUMN_SPLITTER);
        if (words.length > 0) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < words.length; i++) {
                if (i == 0) {
                    res.append(words[i]);
                }
                else {
                    String word = words[i];
                    String newWord = String.valueOf(word.charAt(0)).toUpperCase() + word.substring(1);
                    res.append(newWord);
                }
            }
            return res.toString();
        }
        return column;
    }

    private String firstLetterToUpper(String field) {
        if (StringUtils.isNotBlank(field)) {
            return String.valueOf(field.charAt(0)).toUpperCase() + field.substring(1);
        }
        return field;
    }

    private String simpleType(String classType) {
        String[] type = classType.split(DOT_REG);
        return type[type.length - 1];
    }

    @Test
    void generate() {
        generate("ssm_db", "com.raymond.domain", "book");
    }
}
