const handleConfirmModify = () => {
  $('#modifyTitleModal').modal('hide')
  $('.questionnaire-title > span').text(questionnaireTitle)
  $('.questionnaire-description > span').text(questionnaireDescription)
}

const onQuestionnaireTitleInput = (e) => {
  questionnaireTitle = e.value
}

const onQuestionnaireDescriptionInput = (e) => {
  questionnaireDescription = e.value
}
