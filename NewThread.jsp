<%@ page pageEncoding="Windows-31J"
	contentType="text/html;charset=Windows-31J"%>
<html>
<head><title>�V�K�X���b�h</title>
<!--css-->
<link rel="stylesheet"href="https://unpkg.com/ress/dist/ress.min.css">
<link href="https://fonts.googleapis.com/css?family=Philosopher"rel="stylesheet">
<link href="css/NewThread.css" rel="stylesheet">
<link rel="shortcut icon" href="images/favicon.ico" />
<script>
    if($('#titlecss').val() === ''){
  alert('�^�C�g������͂��Ă��������I');
  $(this).focus();
  return false;
}
if($('#textgo').val() === ''){
  alert('���O����͂��Ă��������I');
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
                <li><a href="login.jsp">���O�C�����</a></li>
                <li><a href="title.jsp">�g�b�v�y�[�W</a></li>
                <li><a href="NewThread.jsp">�X���b�h�쐬</a></li>
                <li><a href="link.jsp">�����N�W</a></li>
            </ul>
        </nav>
    </header>

    <div class="wrap">
    <div class="back">
    <div class="title"><img src="images/title.png" width="600" height="90"></div>
    <div class="nav"><h2>�����͌𗬁E�G�k�E�n��p�̌f���ł��B</h2>
    <h2>�V�K�X���b�h�𗧂Ă�Ƃ��̓X���̃e�[�}��<br>�ړI���͂����肳���Ă��������B</h2>
    </div>
    <br>

    <table class="table1" border='1'>
    <div class="date">
    <form method='post' name='form' action='NThread'>
    <tr> <th><p>�^�C�g��</p></th><th><input id="titlecss" type='text' name='title' width="200" placeholder="�^�C�g������͂��Ă�������"></th></tr>
    <tr><th><p>�^�O</p></th><th>
        <input type='radio' name='tag' value="�A�j��" class='tag'><label for='tagA'>�A�j��</label>
        <input type='radio' name='tag' value="�Q�[��" class='tag'><label for='tagB'>�Q�[��</label>
        <input type='radio' name='tag' value="�J�[�h" class='tag'><label for='tagC'>�J�[�h</label>
        <input type='radio' name='tag' value="�C���X�g" class='tag'><label for='tagD'>�C���X�g</label>
        <input type='radio' name='tag' value="SS" class='tag'><label for='tagE'>SS</label>
        <input type='radio' name='tag' value="��W" class='tag'><label for='tagF'>��W</label>
    </th></tr>
    <tr><th><p>�R�����g</p></th><th><textarea name='comment' cols='50' rows='8'�@placeholder='�R�����g�̓��e����������ł��������B' maxlength="1000" id="textgo"></textarea></th></tr></table>
    </div><a href="title.jsp"><input id="up" type='submit' value='�X���b�h�𐶐�' style="position:absolute; left:500px; top:950px" size="50px"></a>
    </form>
    </div>
    </div><br>
    </div>
</body>
</html>