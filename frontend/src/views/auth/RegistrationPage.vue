<template>
  <base-page>
    <div class="p-6 flex flex-col items-center">
      <card-block class="flex flex-col items-center gap-8 p-6 w-[400px]">
        <span class="text-3xl">
          Регистрация
        </span>

        <form class="w-full flex flex-col gap-4" @submit="submitHandler">
          <label-component label="Логин">
            <text-input-component v-model="user.login"
                                  placeholder="Логин" />
          </label-component>

          <label-component label="Логин гитхаба">
            <text-input-component v-model="user.ghLogin"
                                  placeholder="Логин гитхаба" />
          </label-component>

          <label-component label="Электронная почта">
            <text-input-component v-model="user.email"
                                  placeholder="Электронная почта" />
          </label-component>

          <label-component label="Имя">
            <text-input-component v-model="user.fullName"
                                  placeholder="Имя" />
          </label-component>

          <label-component label="Пароль">
            <password-input-component v-model="user.password"
                                      placeholder="Пароль" />
          </label-component>

          <label-component label="Повторите пароль">
            <password-input-component v-model="user.passwordRepeat"
                                      placeholder="Повтор пароля" />
          </label-component>

          <button-component>
            Зарегистрироваться
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
import CardBlock from '@/components/block/CardBlockComponent.vue';
import TextInputComponent from '@/components/input/TextInputComponent.vue';
import { ref } from 'vue';
import LabelComponent from '@/components/label/LabelComponent.vue';
import ButtonComponent from '@/components/button/ButtonComponent.vue';
import PasswordInputComponent from '@/components/input/PasswordInputComponent.vue';
import { popupState } from '@/services/state/popup';
import doRequest from '@/api/utils/doRequest';
import useRequest from '@/api/hooks/useRequest';
import { useRouter } from 'vue-router';
import { userState } from '@/services/state/user';

const user = ref(
  {
    email: '',
    passwordRepeat: '',
    password: '',
    login: '',
    fullName: '',
    ghLogin: '',
  },
);

const router = useRouter();

const submitHandler = async (event: Event) => {
  event.preventDefault();

  if (Object.values(user.value).every((value: string) => value)) {
    if (user.value.password === user.value.passwordRepeat) {
      await userState.register(user.value);
      await router.push('/auth/login');
    } else {
      popupState.newPopup('Пароли должны совпадать');
    }
  } else {
    popupState.newPopup('Все поля обязательны');
  }
};
</script>

<style></style>
