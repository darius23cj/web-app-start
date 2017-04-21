<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Comenzi IKEA</title>

    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/cards.css">
    <link rel="stylesheet" type="text/css" href="css/agenda.css">

    <link rel="stylesheet" type="text/css" href="css/responsive/max-600px.css" media="(max-width: 600px)">
    <link rel="stylesheet" type="text/css" href="css/responsive/min-1024px.css" media="(min-width: 1024px)">
    <link rel="stylesheet" type="text/css" href="css/fonts/genericons/genericons.css" media="all" />
</head>
<body>

<div id="layout" class="x-borders">
    <header>
        <div>
            <a id="logo" href="." title="FastTrackIT">
                <img src="img/logo.png" alt="FastTrackIT" title="FastTrackIT">
            </a>
            <h1>Proiect comenzi IKEA @ FastTrackIT </h1>
        </div>
    </header>

    <div id="top-menu">
        <div>
            <button id="secondary-toggle" class="genericon genericon-menu">Widgets</button>
            <ul>
                <li><a href="index.jsp" title="bun venit acasa">Acasa</a></li>
                <li><a href="shopping-list.jsp" title="aici poti adauga produse">Lista Cumparaturi</a></li>
                <li><a href="sumar-comanda.jsp" title="aici poti vedea total comanda">Sumar Comanda</a></li>
            </ul>
        </div>
    </div>

    <div id="content-wrapper">

        <div id="left-bar">
            <div id="google-search" class="widget">
                <div class="header">Cauta in ikea.ro</div>
                <div class="content">

                    <a id="logo-ikea" href="." title="FastTrackIT">
                    <img src="img/logo-ikea.png" alt="Ikea" title="Ikea">
                </a>

                    <form action="http://www.ikea.com/ro/ro/search" target="_blank">
                        <input type="text" name="query" id="query">
                        <input type="submit" value="Cauta">
                    </form>
                </div>
            </div>
        </div>
