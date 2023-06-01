
const pathnames = ['/pages/questionnaire/index.html']

const handleHeaderLoad = () => {
  if (pathnames.includes(location.pathname)) {
    $('#handerFallback').remove()
    $('#headerDivB').remove()
  }  
}

const onMyProject = () => {
  if (location.pathname !== '/pages/questionnaire/index.html')
  location.href = "/pages/questionnaire/index.html"
}

const onToUser = () => {
  console.log(location.pathname, 'location.pathname');
  if (location.pathname !== '/pages/user/index.html')
    location.href = "/pages/user/index.html"
}

const onSignOut = () => {
  $util.clearItem()
  location.href = "/pages/login/index.html"
}
