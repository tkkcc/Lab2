$(function () {
    var $books = $('#books');
    var $bookModalLabel = $('#bookModalLabel');
    var $bookModal = $('#bookModal');
    var bookTemplate = $('#bookTemplate').html();
    var authorTemplate = $('#authorTemplate').html();
    // var $searchForm = $('#searchForm');
    var $authorLoading = $('#authorLoading');
    var $target = null;
    var $searchInput = $('#searchInput');

    function myAlert(alert) {
        alert.delay(100).fadeIn(300).delay(3000).fadeOut(300);
    }

    function addBook(book) {
        $books.append(Mustache.render(bookTemplate, book));
    }

    function addAuthor(author) {
        // console.log($bookModal.find('span').attr('class'));
        $authorLoading.hide();
        $bookModalLabel.append(Mustache.render(authorTemplate, author));
    }

    function removeBook() {
        $books.html('');
    }

    function searchBook(name) {
        $.ajax({
            url: '/api/Author!listBooks',
            data: {'author.name': name},
            success: function (json) {
                removeBook();
                var books = json.books;
                $.each(books, function (i, book) {
                    addBook(book);
                });
            },
            error: function () {
                myAlert($('#loadBookError'));
            }
        });
    }

    searchBook(null);

    $searchInput.on("keyup", function (e) {
        clearTimeout($.data(this, 'timer'));
        if (e.keyCode === 13)
            searchBook($(this).val());
        else
            $(this).data('timer', setTimeout(function () {
                searchBook($searchInput.val());
            }, 500));
    });

    $('#addAuthorForm').on('submit', function (e) {
        e.preventDefault();
        var author = {
            'author.authorId': $(this).find('input.author-id').val(),
            'author.name': $(this).find('input.name').val(),
            'author.country': $(this).find('input.country').val(),
            'author.age': $(this).find('input.age').val()
        };
        $.ajax({
            url: '/api/Author!add',
            data: author,
            success: function (book) {
                myAlert($('#addAuthorSuccess'));
            },
            error: function () {
                myAlert($('#addAuthorError'));
            }
        });
    });

    $('#addBookForm').on('submit', function (e) {
        e.preventDefault();
        var book = {
            'book.title': $(this).find('input.title').val(),
            'book.isbn': $(this).find('input.isbn').val(),
            'book.authorId': $(this).find('input.author-id').val(),
            'book.price': $(this).find('input.price').val(),
            'book.publishDate': $(this).find('input.publish-date').val(),
            'book.publisher': $(this).find('input.publisher').val()
        };
        $.ajax({
            //type: 'POST',
            url: '/api/Book!add',
            data: book,
            success: function (book) {
                myAlert($('#addBookSuccess'));
                //在主页才加书
                if ($searchInput.val() === '') {
                    addBook(book.book);
                    $('html, body').animate({scrollTop: $(document).height() - $(window).height()}, 1000);
                }
            },
            error: function () {
                myAlert($('#addBookError'));
            }
        });
    });

    $bookModal.delegate('.delete', 'click', function () {
        var book = {'book.isbn': $target.data('isbn')};
        $.ajax({
            //type: 'DELETE',
            data: book,
            url: '/api/Book!delete',
            success: function () {
                myAlert($('#deleteBookSuccess'));
                $target.delay(300).fadeOut(500, function () {
                    $(this).remove();
                    console.log('delete on html');
                });
                console.log('is deleted?');
            },
            error: function () {
                myAlert($("#deleteBookError"));
            }
        });
    });

    $bookModal.delegate('.update', 'click', function () {
        var book = {
            'book.title': $bookModal.find('input.title').val(),
            'book.isbn': $bookModal.find('input.isbn').val(),
            'book.authorId': $bookModal.find('input.authorId').val(),
            'book.price': $bookModal.find('input.price').val(),
            'book.publishDate': $bookModal.find('input.publishDate').val(),
            'book.publisher': $bookModal.find('input.publisher').val()
        };
        $.ajax({
            url: '/api/Book!update',
            data: book,
            success: function () {
                $target.data('isbn', book['book.isbn']);
                $target.data('title', book['book.title']);
                $target.data('authorid', book['book.authorId']);
                $target.data('publisher', book['book.publisher']);
                $target.data('publishdate', book['book.publishDate']);
                $target.data('price', book['book.price']);
                $target.find('h5').html(book['book.title']);
                myAlert($('#updateBookSuccess'));
            },
            error: function () {
                myAlert($('#updateBookError'));
            }
        });
    });


    //click somewhere else to hide
    $('button').on('click', function (e) {
        $('.collapse').collapse('hide');
    });
    $('#mainDiv').on('click', function (e) {
        // console.log("main div clicked");
        $('.collapse').collapse('hide');
    });

    $bookModal.on('hidden.bs.modal', function (event) {
        $authorLoading.show();
    });
    //modal show
    $bookModal.on('show.bs.modal', function (event) {
        $target = $(event.relatedTarget);// Button that triggered the modal
        $bookModal.find('input.isbn').val($target.data('isbn'));
        $bookModal.find('input.title').val($target.data('title'));
        $bookModal.find('input.authorId').val($target.data('authorid'));
        $bookModal.find('input.publisher').val($target.data('publisher'));
        $bookModal.find('input.publishDate').val($target.data('publishdate'));
        $bookModal.find('input.price').val($target.data('price'));
        $bookModal.find('h5.modal-title').html($target.data('title'));
        $.ajax({
            data: {'author.authorId': $target.data('authorid')},
            url: '/api/Author!getById',
            success: function (json) {
                var author = json.author;
                addAuthor(author);
            },
            error: function () {
                myAlert($('#loadAuthorError'));
            }
        });
    });
});
