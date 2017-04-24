<jsp:include page="header.jsp"/>

<div id="content">
    <div id="breadcrumb">Lista cumparaturi</div>

    <table id="agenda">
        <thead>
        <tr>
            <th>URL-produs</th>
            <th>Cantitate</th>
            <th>Persoana</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>

    <form action="shoppingList" name="addItem" method="post">
        <input type="text" name="produs" placeholder="Introdu URL produs"
               pattern="http://www.ikea.com/ro/ro/catalog/products/[0-9]+/"
               title="Ikea Ro link eg: http://www.ikea.com/ro/ro/catalog/products/1234">
        <input type="number" name="cantitate" placeholder="Introdu cantitate">
        <input type="text" name="persoana" placeholder="Introdu persoana">
        <input type="hidden" name="action" value="add">
        <button>Add</button>
    </form>
</div>

<div class="clear"></div>

</div>
<footer>
    <p>&copy; 2015 FastTrackIT. All rights reserved.</p>
</footer>
</div>

<script src="js/lib/jquery/jquery-2.1.3.js"></script>

<script src="js/code-examples/functions-jquery.js" type="text/javascript"></script>

<script src="js/responsive.js" type="text/javascript"></script>

<script src="js/shopping-list.js"></script>

</body>
</html>