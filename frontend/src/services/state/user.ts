import { computed, ref } from 'vue';
import {
  ERole,
  type ITokenCouple,
  type ITokenRefresh,
  type IUser,
  type IUserCreate,
  type IUserCredentials,
} from '@/api/user/types';
import useRequest from '@/api/hooks/useRequest';

const REFRESH_TOKEN_KEY = "refresh_token";

const userStateSetup = () => {
  const userInfo = ref<IUser>();
  const tokenCouple = ref<ITokenCouple>();

  const getUserInfo = computed(() => userInfo.value);

  const isLoggedIn = computed(() => !!tokenCouple.value);

  const getAccessToken = computed(() => tokenCouple.value?.accessToken ?? '');
  const getRefreshToken = computed(() => tokenCouple.value?.refreshToken ?? '');

  const getIsAdmin = computed(() => userInfo.value?.role === ERole.ADMIN);

  const refreshTimer = ref<number>();

  const login = async (userCredentials: IUserCredentials) => {
    tokenCouple.value = await useRequest().post('/auth/login', userCredentials);
    localStorage.setItem(REFRESH_TOKEN_KEY, getRefreshToken.value);
    await loadUserInfo();
    await refresh(getRefreshToken.value, true);
  }

  const register = async (userInfo: IUserCreate) => {
    await useRequest().post('/auth/registration', userInfo);
  }

  const refresh = async (refreshToken: string, onlyTimer?: boolean) => {
    if (!onlyTimer) {
      tokenCouple.value = await useRequest().post('/auth/refresh', { refreshToken } as ITokenRefresh);
      localStorage.setItem(REFRESH_TOKEN_KEY, getRefreshToken.value);
    }
    refreshTimer.value = setTimeout(async () => {
      await refresh(getRefreshToken.value);
    }, 60000)
  }

  const loadUserInfo = async () => {
    if (isLoggedIn.value) {
      userInfo.value = await useRequest().get('/account/me');
    }
  }

  const init = async () => {
    if (userInfo.value) return;
    if (isLoggedIn.value) {
      await loadUserInfo();
    } else {
      const refreshToken = localStorage.getItem(REFRESH_TOKEN_KEY);
      if (refreshToken) {
        await refresh(refreshToken);
        await loadUserInfo();
      }
    }
  }

  const logout = async () => {
    clearTimeout(refreshTimer.value);
    refreshTimer.value = undefined;
    tokenCouple.value = undefined;
    localStorage.removeItem(REFRESH_TOKEN_KEY);
  }

  return {
    getUserInfo,
    getAccessToken,
    isLoggedIn,
    getIsAdmin,

    register,
    login,
    logout,
    loadUserInfo,
    init,
  }
};

export const userState = userStateSetup();
