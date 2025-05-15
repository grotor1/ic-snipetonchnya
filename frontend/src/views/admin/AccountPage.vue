<template>
  <base-page>
    <div class="grid grid-cols-8 items-center justify-items-center p-6">
      <div v-for="(value, key) in accounts[0]" class="p-3" :key="key">
        {{ key }}
      </div>

      <div class="p-3">
        Действие
      </div>

      <template v-for="account in accounts" :key="account.id">
        <div v-for="(value, key) in account" class="p-3" :key="key">
          <template v-if="key === 'login'">
            <router-link :to="`/auth/account/${account.id}`" class="hover:underline">
              {{value}}
            </router-link>
          </template>

          <template v-else>
            {{ value }}
          </template>
        </div>

        <div class="py-3">
          <button-component v-if="account.role !== ERole.ADMIN"
                            @click="() => toggleBlockUser(account.id)">
            {{ account.blocked ? 'Разблокировать' : 'Заблокировать'}}
          </button-component>
        </div>
      </template>
    </div>
  </base-page>
</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">
import BasePage from '@/views/BasePage.vue';
import { ref } from 'vue';
import { ERole, type IUser } from '@/api/user/types';
import useRequest from '@/api/hooks/useRequest';
import ButtonComponent from '@/components/button/ButtonComponent.vue';
import type { TId } from '@/api/default/types';

const accounts = ref<IUser[]>([]);

const loadAccounts = async () => {
  accounts.value = await useRequest().get('/account');
};

loadAccounts();

const toggleBlockUser = async (id: TId) => {
  await useRequest().post(`/account/${id}/toggle-block`);
  await loadAccounts();
};
</script>

<style>

</style>
