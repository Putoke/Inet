<%@ page import="java.util.ArrayList"%>
<%@ page import="bean.*" %>

<html>

    <head>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <title>Slask-Trade 0.1 Beta</title>
    </head>

    <body>

        <h3>Addera ett värdepapper</h3>
        <form action="/TradeController">
            <input type="hidden" name="action" value="addSecurity">
            <input type="text" name="security" value=""><br>
            <input type="submit" value="Utför">
        </form>

    <h3>Lägg en köp/säljorder på ett värdepapper</h3>
    <form action="/TradeController">
        <input type="hidden" name="action" value="addOrder">
        Värdepapper: <select name="security">
            <%
                SecurityDB db = new SecurityDB();
                ArrayList<Security> securities = db.getSecurities();
                for(Security s : securities) {
                    out.println("<option value=\"" + s.getName() + "\">" + s.getName() + "</option>");
                }
            %>
        </select><br>
        Köp: <input type="radio" name="buyOrSell" value="B" checked>
        Sälj: <input type="radio" name="buyOrSell" value="S"><br>
        Pris: <input type="text" name="price" value=""><br>
        Antal: <input type="text" name="amount" value=""><br>
        <input type="submit" value="Utför">
    </form>

    <h3>Visa avslutade affärer i ett värdepapper</h3>
    <form action="/TradeController">
        <input type="hidden" name="action" value="viewTrades">
        Värdepapper: <select name="security">
            <%
                for(Security s : securities) {
                    out.println("<option value=\"" + s.getName() + "\">" + s.getName() + "</option>");
                }
                db.close();
            %>
        </select><br>
        <input type="submit" value="Utför">
    </form>

        <%
            String message = request.getParameter("message");
            if (message != null && message.equals("viewTrades")) {
                TradeDB tdb = new TradeDB();
                ArrayList<Trade> trades = tdb.getTrades(request.getParameter("security"));
                message = "<table border=1><tr><td><b>Id</b></td><td><b>Namn</b></td><td><b>Pris</b></td><td><b>Antal</b></td><td><b>Datum</b></td><td><b>Köpare</b></td><td><b>Säljare</b></td></tr>";
                for (Trade t : trades) {
                    message += "<tr><td>" + t.getId() + "</td><td>" + t.getName() + "</td><td>" + t.getPrice() + "</td><td>" + t.getAmount() + "</td><td>" + t.getDt() + "</td><td>" + t.getBuyer() + "</td><td>" + t.getSeller() + "</td></tr>";
                }
                message += "</table>";
                tdb.close();
            }
            
            out.println(message);

        %>

    </body>

</html>
