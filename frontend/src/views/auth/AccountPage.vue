<template>
  <base-client-page>
    <div class="flex flex-col p-6 gap-4 h-full overflow-y-auto">
      <card-block-component class="p-6 flex flex-col w-full">
        <span class="text-2xl">
           {{ getUserInfo?.login }} {{ getUserInfo?.fullName && '- ' + getUserInfo.fullName }}
        </span>

        <span>
           Email: {{ getUserInfo?.email }}
        </span>

        <span v-if="userGh">
           Подписчики на гх: {{ userGh.followers }}
        </span>
      </card-block-component>

      <span class="text-2xl">
        Посты:
      </span>

      <post-list-component :params="{author: getUserInfo?.id}"/>
    </div>
  </base-client-page>
</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">
import CardBlockComponent from '@/components/block/CardBlockComponent.vue';
import {userState} from '@/services/state/user';
import {useRoute} from 'vue-router';
import {computed, ref} from 'vue';
import type {IUser} from '@/api/user/types';
import useRequest from '@/api/hooks/useRequest';
import BaseClientPage from '@/views/client/BaseClientPage.vue';
import PostListComponent from "@/components/list/PostListComponent.vue";

const userInfo = ref<IUser>();

const userGh = ref();

const route = useRoute();
const {getUserInfo: getMeUserInfo} = userState;

const getIsExternal = computed(() => route.params.id !== 'me');

const getUserInfo = computed(() => getIsExternal.value ? userInfo.value : getMeUserInfo.value);

const loadUserInfo = async () => {
  if (getIsExternal.value) {
    userInfo.value = await useRequest().get(`/account/${route.params.id}`);
  }

  if (getUserInfo.value?.ghLogin) {
    userGh.value = await (await fetch(`https://api.github.com/users/${getUserInfo.value.ghLogin}`, {
      method: 'GET',
      headers: {
        'Accept': 'application/vnd.github+json',
        'X-GitHub-Api-Version': '2022-11-28',
      }
    })).json();
  }
};

loadUserInfo();
</script>

<style>

</style>
