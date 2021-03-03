<%@ page pageEncoding="Windows-31J"
	contentType="text/html;charset=Windows-31J"%>
<html>
<head><title>新規スレッド</title>
<!--css-->
<link rel="stylesheet"href="https://unpkg.com/ress/dist/ress.min.css">
<link href="https://fonts.googleapis.com/css?family=Philosopher"rel="stylesheet">
<link href="css/NewThread.css" rel="stylesheet">
<link rel="shortcut icon" href="images/favicon.ico" />
<script>
    if($('#titlecss').val() === ''){
  alert('タイトルを入力してください！');
  $(this).focus();
  return false;
}
if($('#textgo').val() === ''){
  alert('名前を入力してください！');
  $(this).focus();
  return false;
}
</script>
</head>
<body>
    <header>
        <img class="titlehead"  src="images/header.png" position="absolute" top="0px" left="0px" width="200">
        <nav class="pc-nav">
            <ul>
                <li><a href="login.jsp">ログイン画面</a></li>
                <li><a href="title.jsp">トップページ</a></li>
                <li><a href="NewThread.jsp">スレッド作成</a></li>
                <li><a href="link.jsp">リンク集</a></li>
            </ul>
        </nav>
    </header>

    <div class="wrap">
    <div class="back">
    <div class="title"><img src="images/title.png" width="600" height="90"></div>
    <div class="nav"><h2>ここは交流・雑談・創作用の掲示板です。</h2>
    <h2>新規スレッドを立てるときはスレのテーマや<br>目的をはっきりさせてください。</h2>
    </div>
    <br>

    <table class="table1" border='1'>
    <div class="date">
    <form method='post' name='form' action='NThread'>
    <tr> <th><p>タイトル</p></th><th><input id="titlecss" type='text' name='title' width="200" placeholder="タイトルを入力してください"></th></tr>
    <tr><th><p>タグ</p></th><th>
        <input type='radio' name='tag' value="アニメ" class='tag'><label for='tagA'>アニメ</label>
        <input type='radio' name='tag' value="ゲーム" class='tag'><label for='tagB'>ゲーム</label>
        <input type='radio' name='tag' value="カード" class='tag'><label for='tagC'>カード</label>
        <input type='radio' name='tag' value="イラスト" class='tag'><label for='tagD'>イラスト</label>
        <input type='radio' name='tag' value="SS" class='tag'><label for='tagE'>SS</label>
        <input type='radio' name='tag' value="募集" class='tag'><label for='tagF'>募集</label>
    </th></tr>
    <tr><th><p>コメント</p></th><th><textarea name='comment' cols='50' rows='8'　placeholder='コメントの内容を書き込んでください。' maxlength="1000" id="textgo"></textarea></th></tr></table>
    </div><a href="title.jsp"><input id="up" type='submit' value='スレッドを生成' style="position:absolute; left:500px; top:950px" size="50px"></a>
    </form>
    </div>
    </div><br>
    </div>
</body>
</html>