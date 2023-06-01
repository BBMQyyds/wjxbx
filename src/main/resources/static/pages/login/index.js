const onLogin = () => {
  let params = {
    username: $('#username').val().trim(),
    password: $('#password').val().trim()
  }
  if (!params.username) return alert('请输入用户名！')
  if (!params.password) return alert('请输入密码！')
  $.ajax({
    url: API_BASE_URL + '/admin/userLogin',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      if (res.code === '666') {
        $util.setItem('userInfo', res.data)
        location.href = "/pages/questionnaire/index.html"
      } else {
        alert(res.message)
      }
    }
  })
}

