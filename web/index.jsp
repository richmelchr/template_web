<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SER322 Final</title>
    <link href="css.css" rel="stylesheet" type="text/css" media="screen">
</head>
<body>

<div id="wrap" class="something">

    <div>
        <h1>Writer</h1>
        <form id="tableWriter" action="Servlet"> <!--not sure if fields used-->
            <%--suppress XmlDuplicatedId --%>
            <fieldset id="tableInput">

                <div>
                    <h2>writerName:</h2>
                    <textarea id="writerName" class="forumInput"></textarea>
                </div>

                <a href="#" type="submit" id="writerBtn" class="myButton right" tabindex="0">Submit</a>

            </fieldset>
        </form>
    </div>

    <div>
        <h1>Song</h1>
        <form id="tableSong" action="Servlet"> <!--not sure if fields used-->
            <%--suppress XmlDuplicatedId --%>
            <fieldset id="tableInput">
                <div>
                    <h2>songName:</h2>
                    <textarea id="songName"></textarea>
                </div>
                <div>
                    <h2>yearWritten:</h2>
                    <textarea id="yearWritten"></textarea>
                </div>
                <div>
                    <h2>length:</h2>
                    <textarea id="length"></textarea>
                </div>
                <div>
                    <h2>genre:</h2>
                    <textarea id="genre"></textarea>
                </div>
                <div>
                    <h2>writerID:</h2>
                    <textarea id="writerID"></textarea>
                </div>
                <div>
                    <h2>artistID:</h2>
                    <textarea id="artistID"></textarea>
                </div>
                <div>
                    <h2>albumID:</h2>
                    <textarea id="albumID"></textarea>
                </div>

                <a href="#" type="submit" id="songBtn" class="myButton right" tabindex="0">Submit</a>

            </fieldset>
        </form>
    </div>

    <div>
        <h1>Album</h1>
        <form id="tableAlbum" action="Servlet"> <!--not sure if fields used-->
            <%--suppress XmlDuplicatedId --%>
            <fieldset id="tableInput">
                <div>
                    <h2>albumName:</h2>
                    <textarea id="albumName" class="forumInput"></textarea>
                </div>
                <div>
                    <h2>yearRel:</h2>
                    <textarea id="yearRel" class="forumInput"></textarea>
                </div>

                <a href="#" type="submit" id="albumBtn" class="myButton right" tabindex="0">Submit</a>

            </fieldset>
        </form>
    </div>

    <div>
        <h1>Artist</h1>
        <form id="tableArtist" action="Servlet"> <!--not sure if fields used-->
            <%--suppress XmlDuplicatedId --%>
            <fieldset id="tableInput">

                <div>
                    <h2>artistName:</h2>
                    <textarea id="artistName" class="forumInput"></textarea>
                </div>

                <a href="#" type="submit" id="artistBtn" class="myButton right" tabindex="0">Submit</a>

            </fieldset>
        </form>
    </div>

    <div id="lookup">
        <h3>Lookup</h3>
        <form id="tableLookup" action="Servlet">
            <%--suppress XmlDuplicatedId --%>
            <fieldset id="tableInput">

                <div>
                    <h2>SELECT:</h2>
                    <textarea id="selectTable" class="forumInput"></textarea>
                </div>

                <div>
                    <h2>FROM:</h2>
                    <textarea id="fromTable" class="forumInput"></textarea>
                </div>

                <div>
                    <h2>WHERE:</h2>
                    <textarea id="whereTable" class="forumInput"></textarea>
                </div>

                <a href="#" type="submit" id="lookupBtn" class="myButton right" tabindex="0">Lookup</a>

            </fieldset>
        </form>
    </div>

    <div id="radioSelect">
        <ul>
            <li><a>1</a><input type="radio" name="radio" value="1" checked></li>
            <li><a>2</a><input type="radio" name="radio" value="2"></li>
            <li><a>3</a><input type="radio" name="radio" value="3"></li>
            <li><a>4</a><input type="radio" name="radio" value="4"></li>
            <li><a>5</a><input type="radio" name="radio" value="5"></li>
        </ul>

        <a href="#" type="submit" id="radioBtn" class="myButton right" tabindex="0">Lookup</a>
    </div>


    <div id="result">
        <table>
            <tbody>

            </tbody>

        </table>
    </div>


</div>

</body>
</html>

<script src="jquery-2.2.0.min.js"></script>
<script src="jquery.js"></script>


