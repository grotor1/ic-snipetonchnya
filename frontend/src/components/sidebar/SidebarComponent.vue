<template>
  <div class="w-full border-r h-full border-stone-700 p-6 relative">
    <div class="flex flex-col gap-4">
      <router-link class="hover:underline" to="/">
        <span>
          Лента
        </span>
      </router-link>

      <router-link class="hover:underline" to="/search">
        <span>
          Поиск
        </span>
      </router-link>

      <router-link v-if="userState.isLoggedIn.value" class="hover:underline" to="/searches-list">
        <span>
          Сохраненные поиски
        </span>
      </router-link>
    </div>

    <router-link v-if="userState.isLoggedIn.value"
                 id="create-post-link"
                 class="absolute bottom-2 left-4 right-4 flex p-3 rounded-lg border border-stone-700 hover:underline justify-between"
                 to="/create-post">
        <span>
          Создать пост
        </span>

      <span>
          +
        </span>
    </router-link>
  </div>
</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">

import { userState } from '@/services/state/user';
</script>

<style>

</style>
