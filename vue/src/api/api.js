import axios from 'axios';

/*let base = '';

export const requestLogin = params => { return axios.post(`${base}/login`, params).then(res => res.data); };

export const getUserList = params => { return axios.get(`${base}/user/list`, { params: params }); };

export const getUserListPage = params => { return axios.get(`${base}/user/listpage`, { params: params }); };

export const removeUser = params => { return axios.get(`${base}/user/remove`, { params: params }); };

export const batchRemoveUser = params => { return axios.get(`${base}/user/batchremove`, { params: params }); };

export const editUser = params => { return axios.get(`${base}/user/edit`, { params: params }); };

export const addUser = params => { return axios.get(`${base}/user/add`, { params: params }); };

*/

axios.interceptors.response.use(
    response => {
        return response
    },
    error => {
        //alert("网络出错了..");
        
        let res ={code:100,msg:'network error',data:[]};
        return {data:res}
       
    })

let server_base = '/TeamTalk/users';

export const loginRequest = params => { return axios.get(`${server_base}/login`, {params:params}).then(res => res.data); };

export const modifyRequest = params =>{ return axios.post(`${server_base}/modify`,params).then(res => res.data); }; 

export const addRequest = params => { return axios.post(`${server_base}/admin/add`, params).then(res => res.data); };


export const listUsersRequest = params => { return axios.get(`${server_base}/user/list`, params).then(res => res.data); };

export const addUserRequest = params => { return axios.post(`${server_base}/user/add`, params).then(res => res.data); };

export const removeUserRequest = params => { return axios.post(`${server_base}/user/remove`, {params:params}).then(res => res.data); };

export const updateUserRequest = params => { return axios.post(`${server_base}/user/update`, params).then(res => res.data); };

export const updatePasswordRequest = params => { return axios.post(`${server_base}/user/updatePassword`, params).then(res => res.data); };



export const listDepartRequest = params => { return axios.get(`${server_base}/depart/list`, params).then(res => res.data); };

export const addDepartRequest = params => { return axios.post(`${server_base}/depart/add`, params).then(res => res.data); };

export const removeDepartRequest = params => { return axios.post(`${server_base}/depart/remove`, {params:params}).then(res => res.data); };

export const updateDepartRequest = params => { return axios.post(`${server_base}/depart/update`, params).then(res => res.data); };



export const listGroupRequest = params => { return axios.get(`${server_base}/group/list`, params).then(res => res.data); };

export const addGroupRequest = params => { return axios.post(`${server_base}/group/add`, params).then(res => res.data); };

export const removeGroupRequest = params => { return axios.post(`${server_base}/group/remove`, {params:params}).then(res => res.data); };

export const updateGroupRequest = params => { return axios.post(`${server_base}/group/update`, params).then(res => res.data); };


export const listDiscoveryRequest = params => { return axios.get(`${server_base}/discovery/list`, params).then(res => res.data); };

export const addDiscoveryRequest = params => { return axios.post(`${server_base}/discovery/add`, params).then(res => res.data); };

export const removeDiscoveryRequest = params => { return axios.post(`${server_base}/discovery/remove`, {params:params}).then(res => res.data); };

export const updateDiscoveryRequest = params => { return axios.post(`${server_base}/discovery/update`, params).then(res => res.data); };
