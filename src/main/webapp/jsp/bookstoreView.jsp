<%-- 
    Document   : bookstoreView
    Created on : 16.03.2020, 15:01:20
    Author     : timon_kaufmann
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="servlet.Book"%>
<%@page import="servlet.Publisher"%>
<%@page import="java.util.List"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            #Bookstore th{
                padding-top: 12px;
                padding-bottom: 12px;
                text-align: center;
                color: white;   
            }

        </style> 
    </head>
    <body bgcolor="#A8BBCC">
        <% List<Publisher> publisher = (List<Publisher>) application.getAttribute("publisherList");
            System.out.println(publisher.toString());
            Integer id = Integer.parseInt(application.getAttribute("offset").toString());
            List<Publisher> publisheranzeige = new ArrayList<>();
        %>
    <center><h1 style="color: green">Bookstore</h1></center>
    <center><h4 style="color: white; background-color: blue" >Iterate Publisher</h4></center>
    <center> 
        <form id="eingabeForm" action="BookstoreDispather" method="POST"> 
            <table border="1" id="bookstore">
                <tr>
                    <th style="width: 70px"><button type="submit" for="eingabeForm" id="Previous" name="cmd" value="Previous" >&#8656;</button></th>
                    <th style="width: 70px">ID</th>
                    <th  style="width: 600px; ">Name</th>
                    <th style="width: 70px"><button type="submit" for="eingabeForm" id="Next" name="cmd" value="Next" >&#8658;</button></th>
                </tr>
                <% int endei = id + 3;
                    for (int i = id; i < endei; i++) {

                        int count = publisher.size();
                        if (i >= count) {
                            i = count - 3;
                            endei = count;
                %>
                <strong><p style="background-color: white; color:red">Ende der Publisher erreicht</p></strong>
                    <%
                        }

                        if (i < 0) {
                            i = 0;
                            endei = 3;
                    %>
                <strong><p style="background-color: white; color:red">Anfang der Publisher erreicht</p></strong>
                <%
                    }

                %>
                <tr>
                    <td></td>
                    <td style="text-align: center"><%=publisher.get(i).getPublisher_id()%></td>
                    <td name="name" style="text-align: center"><%=publisher.get(i).getName()%></td>
                    <td><button type="submit" for="eingabeForm" id="findboock" name="cmd" value="findboock" >Find Books</button></td>

                </tr>
                <%
                    }
                    for (int i = 0; i < 3; i++) {

                    }
                %>
            </table>  
            </br>
            <%  String namep
                        = ((String) application.getAttribute("publishername") != null)
                        ? (String) application.getAttribute("publishername") : "PublisherName";
            %>
            <center><h4 style="color: white; background-color: blue" >Books from Publisher: <%=namep%></h4></center>

            <table border="1" id="table" style="background-color: yellow">
                <tr>
                    <th style="width: 600px">Title</th>
                    <th style="width: 70px">Price</th>
                    <th  style="width: 200px; ">ISBN</th>
                </tr>
                <%
                    List<Book> books = (List<Book>) application.getAttribute("books");
                    if (books.isEmpty()) {
                        System.out.println("Noch wurde kein Button gedrückt!");
                    } else {
                        for (Book book : books) {

                %>
                <tr> 
                    <td style="text-align: left"><%=book.getTitle()%></td>
                    <td name="name" style="text-align: left"><%=book.getPrice()%></td>
                    <td name="name" style="text-align: left"><%=book.getIsbn()%></td>
                </tr>
                <%
                        }
                    }
                %>
            </table>  
            <input type="hidden" id="id1" name="ausgabe"/>
        </form>
        <script>

            var table = document.getElementById("bookstore"), rIndex, cIndex;
            var inputF = document.getElementById("id1");

            // table rows
            for (var i = 1; i < table.rows.length; i++)
            {
                // row cells
                for (var j = 0; j < table.rows[i].cells.length; j++)
                {

                    table.rows[i].cells[j].onclick = function ()
                    {

                        var rIndex = this.parentElement.rowIndex;
                        cIndex = this.cellIndex + 1;
                        console.log("Row : " + rIndex + " , Cell : " + cIndex);
                        document.getElementById("id1").value = table.rows[rIndex].cells[1].innerHTML + "#" + table.rows[rIndex].cells[2].innerHTML;

                    };
                }
            }


        </script>  
    </body>

</html>
