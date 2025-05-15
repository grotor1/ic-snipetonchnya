<template>
  <card-block-component class="p-2 min-w-[300px] max-w-[100vw-40px] text-amber-100">
    {{message}}
  </card-block-component>
</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">
import CardBlockComponent from '@/components/block/CardBlockComponent.vue';

interface IProps {
  message: string,
}

defineProps<IProps>()
</script>

<style>

</style>
