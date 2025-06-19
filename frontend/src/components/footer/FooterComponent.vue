<template>
  <div
      class="md:hidden h-14 w-full fixed bottom-0 left-0 bg-stone-900 border-t border-stone-700 flex gap-8 justify-around p-2 items-center">
    <router-link to="/" class="leading-none">
      <span class="material-symbols-outlined text-[32px] leading-none">
        post
      </span>
    </router-link>


    <router-link to="/search" class="leading-none">
      <span class="material-symbols-outlined text-[32px]">
        search
      </span>
    </router-link>

    <div v-if="userState.isLoggedIn.value" class="relative w-20 h-full">
      <router-link to="/create-post" class="-translate-y-1/2 absolute z-10 -top-2 w-20 h-20 bg-stone-900 border border-stone-700 rounded-full flex justify-center items-center">
        <span class="material-symbols-outlined text-[40px] leading-none">
          add_2
        </span>
      </router-link>
    </div>

    <router-link v-if="userState.isLoggedIn.value" to="/searches-list" class="leading-none">
      <span class="material-symbols-outlined text-[32px] leading-none">
        saved_search
      </span>
    </router-link>

    <router-link v-if="userState.isLoggedIn.value" to="/auth/account/me" class="leading-none">
      <span class="material-symbols-outlined text-[32px] leading-none">
        account_box
      </span>
    </router-link>

    <router-link v-else to="/auth/login" class="leading-none">
      <span class="material-symbols-outlined text-[32px] leading-none">
        login
      </span>
    </router-link>
  </div>
</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">

import {userState} from "@/services/state/user";
</script>

<style>

</style>
