package data;

class ResponseBody {

    private final pagination pagination;
    private final data[] data;

    ResponseBody(
            pagination pagination,
            data[] data
    ){
        this.pagination = pagination;
        this.data = data;
    }

    pagination getPagination() {
        return pagination;
    }

    data[] getData(){
        return data;
    }

}
