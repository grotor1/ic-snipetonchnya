<template>
  <div class="w-full flex justify-between items-center p-4 border-b border-stone-700 bg-stone-900">
    <router-link to="/">
      <span class="text-2xl hover:underline">
        Снипетошная
      </span>
    </router-link>

    <div class="flex items-center justify-center gap-3">
      <template v-if="!isLoggedIn">
        <router-link id="login-link" to="/auth/login">
          <span class="hover:underline">
            Вход
          </span>
        </router-link>

        <router-link id="registration-link" to="/auth/registration">
          <span class="hover:underline">
            Регистрация
          </span>
        </router-link>
      </template>

      <template v-else>
        <router-link to="/auth/account/me" id="account-link">
          <span class="hover:underline">
            Аккаунт -
            <span id="account-login">
              {{ getUserInfo?.login }}
            </span>
          </span>
        </router-link>

        <span id="logout-button" @click="redirectLogout" class="hover:underline">
          Выход
        </span>
      </template>
    </div>
  </div>
</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">
import { userState } from '@/services/state/user';
import { useRouter } from 'vue-router';

const router = useRouter();

const { isLoggedIn, logout, getUserInfo } = userState;

const redirectLogout = async () => {
  await logout();
  router.push('/');
}
</script>

<style>

</style>
