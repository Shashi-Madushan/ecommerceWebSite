(function (){

    // Handle update button click
    $('.update-btn').click(function () {
        $('#updateModal').modal('show');
        // Populate the form with existing product data
        // This is just a placeholder, replace with actual data retrieval logic
        $('#productName').val('Product A');
        $('#productDescription').val('Sample description');
        $('#productPrice').val('10.00');
        $('#productStock').val('100');
    });

    // Handle delete button click
    $('.delete-btn').click(function () {
        $('#deleteModal').modal('show');
    });

    // Handle form submission for update
    $('#updateForm').submit(function (e) {
        e.preventDefault();
        // Perform update operation
        alert('Product updated successfully!');
        $('#updateModal').modal('hide');
    });

    // Handle delete confirmation
    $('#confirmDelete').click(function () {
        // Perform delete operation
        alert('Product deleted successfully!');
        $('#deleteModal').modal('hide');
    });

})();