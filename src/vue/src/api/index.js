import axios from 'axios'
import {eventBus} from '@ai-zen/event-bus';

const request = axios.create({
    timeout: 10000,
    withCredentials: true,
    headers: {'Content-Type': 'application/json;charset=utf-8'}
})

export default request
