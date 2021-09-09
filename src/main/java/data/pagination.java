package data;

class pagination {

    private final int limit;
    private final int offset;
    private final int count;
    private final int total;

    pagination(
            int limit,
            int offset,
            int count,
            int total
    ) {
        this.limit = limit;
        this.offset = offset;
        this.count = count;
        this.total = total;
    }

    int getLimit() {
        return limit;
    }

    int getOffset() {
        return offset;
    }

    int getCount() {
        return count;
    }

    int getTotal() {
        return total;
    }

}
