/**
 * 调用后台批量删除方法
 */
function deleteBatch(basePath) {
    $('#mainForm').attr("action", basePath + "DeleteBatch.action").submit();
}

/**
 * 改变当前页数
 */
function changeCurrentPage(currentPage) {
    $('#currentPage').val(currentPage);
    $('#mainForm').submit();
}