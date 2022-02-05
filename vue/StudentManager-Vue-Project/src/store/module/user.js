
import Vue from "vue";
import { getInfo, login, logout } from "../../api/user";
import {
    getAccessToken,
    removeAccessToken,
    setAccessToken,
} from "../../utils/accessToken";
import { resetRouter } from "../../router";
import { title, tokenName } from "../../config/settings";


const state = {
    accessToken: getAccessToken(),
    username: '201821122118',
    avatar: "",
    permissions: [],
    power: 1,
};
const getters = {
    accessToken: (state) => state.accessToken,
    username: (state) => state.username,
    avatar: (state) => state.avatar,
    permissions: (state) => state.permissions,
};
const mutations = {
    setAccessToken(state, accessToken) {
        state.accessToken = accessToken;
        setAccessToken(accessToken);
    },
    setUserName(state, username) {
        state.username = username;
    },
    setAvatar(state, avatar) {
        state.avatar = avatar;
    },
    setPermissions(state, permissions) {
        state.permissions = permissions;
    },
};
const actions = {
    async login({ commit }, userInfo) {
        const { data } = await login(userInfo);
        const accessToken = data[tokenName];
        if (accessToken) {
            commit("setAccessToken", accessToken);
            const hour = new Date().getHours();
            Vue.prototype.$baseNotify('欢迎登录${title}' );
        } else {
            Vue.prototype.$baseMessage(
                '登录接口异常，未正确返回${tokenName}...',
                "error"
            );
        }
    },
    async getInfo({ commit, state }) {
        const { data } = await getInfo(state.accessToken);
        if (!data) {
            Vue.prototype.$baseMessage("验证失败，请重新登录...", "error");
            return false;
        }
        let { permissions, userName, avatar } = data;
        if (permissions && userName) {
            commit("setPermissions", permissions);
            commit("setUserName", userName);
            commit("setAvatar", avatar);
            return permissions;
        } else {
            Vue.prototype.$baseMessage("获取用户信息接口异常", "error");
            return false;
        }
    },
    async logout({ dispatch }) {
        await logout(state.accessToken);
        await dispatch("tagsBar/delAllRoutes", null, { root: true });
        await dispatch("resetAccessToken");
        await resetRouter();
    },
    resetAccessToken({ commit }) {
        commit("setPermissions", []);
        commit("setAccessToken", "");
        removeAccessToken();
    },
    async getUserName({dispatch}){
        return state.username;
    }
};
export default { state, getters, mutations, actions };

