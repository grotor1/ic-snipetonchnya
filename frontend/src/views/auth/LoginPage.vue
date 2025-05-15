<template>
  <base-page>
    <div class="p-6 flex flex-col items-center">
      <card-block class="flex flex-col items-center gap-8 p-6 min-w-[400px]">
        <span class="text-3xl">
          Регистрация
        </span>

        <form class="w-full flex flex-col gap-4" @submit="submitHandler">
          <label-component label="Логин">
            <text-input-component id="login-input" v-model="credentials.login"
                                  placeholder="Логин" />
          </label-component>

          <label-component label="Пароль">
            <password-input-component id="password-input" v-model="credentials.password"
                                      placeholder="Пароль" />
          </label-component>

          <button-component id="login-button">
            Войти
          </button-component>
        </form>
      </card-block>
    </div>
  </base-page>
</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">

import BasePage from '@/views/BasePage.vue';
import TextInputComponent from '@/components/input/TextInputComponent.vue';
import LabelComponent from '@/components/label/LabelComponent.vue';
import CardBlock from '@/components/block/CardBlockComponent.vue';
import ButtonComponent from '@/components/button/ButtonComponent.vue';
import PasswordInputComponent from '@/components/input/PasswordInputComponent.vue';
import type { IUserCredentials } from '@/api/user/types';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import useRequest from '@/api/hooks/useRequest';
import { popupState } from '@/services/state/popup';
import { userState } from '@/services/state/user';


const credentials = ref<IUserCredentials>(
    {
      login: '',
      password: '',
    }
)

const router = useRouter();

const submitHandler = async (event: Event) => {
  event.preventDefault();

  if (Object.values(credentials.value).every((value: string) => value)) {
    await userState.login(credentials.value);
    await router.push('/');
  } else {
    popupState.newPopup('Все поля обязательны');
  }
};
</script>

<style>

</style>
