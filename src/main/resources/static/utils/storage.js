export const setItem = (key, value) => {
  let valStr = value
  if (typeof value !== 'string') valStr = JSON.stringify(value)
  localStorage.setItem(key, valStr)
}

export const getItem = (key) => {
  try {
    let value = JSON.parse(localStorage.getItem(key))
    return value
  } catch (err) {
    return localStorage.getItem(key)
  }
}

export const clearItem = () => {
  localStorage.clear()
}

export const setPageParam = (key, value) => {
  setItem('pageParams', {
    ...getItem('pageParams'),
    [key]: value
  })
}

export const getPageParam = (key) => {
  let params = getItem('pageParams')
  return params[key]
}
