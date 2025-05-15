<template>
  <div class="absolute top-2 right-1/2 translate-x-1/2 flex flex-col gap-2">
    <popup-component v-for="(item, index) in popupState.getPopups.value"
                     :key="index"
                     :message="item"/>
  </div>
</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">
import PopupComponent from '@/components/popup/PopupComponent.vue';
import { popupState } from '@/services/state/popup';
</script>

<style>

</style>
