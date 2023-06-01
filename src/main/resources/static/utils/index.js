import * as storage from './storage.js'
import { loginCheck } from './app.js'


loginCheck()

window.$util = {
  ...storage
}
