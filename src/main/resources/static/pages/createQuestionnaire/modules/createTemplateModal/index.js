const handleConfirm = () => {
  let params = {
    type: $('#type').val(),
    templateName: $('#templateName').val(),
    templateDescription: $('#templateDescription').val()
  }
  if (!params.type) return alert('所属类型不能为空！')
  if (!params.templateName) return alert('模板名称不能为空！')
  if (!params.templateDescription) return alert('模板说明不能为空！')

  $.ajax({
    url: API_BASE_URL + '/addQuestionnaire',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/jsoresn",
    success(res) {
      console.log(res)
      $('#createTemplateModal').modal('hide')
      alert('创建成功！')
    }
  })
}
