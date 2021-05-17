package io.github.mavenreposs.royalcms.model;


import java.util.List;

public class QueryResult<T> {

    private List<T> rows;

    private long total;

    public QueryResult(List<T> rows, long total) {
        this.rows = rows;
        this.total = total;
    }

    public QueryResult() {
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
