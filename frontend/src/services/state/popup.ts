import { computed, ref } from 'vue';

export const popupStateSetup = () => {
  const popupQueue = ref<string[]>([]);

  const newPopup = (message: string) => {
    popupQueue.value.push(message)

    setTimeout(() => {
      popupQueue.value.shift();
    }, 30000);
  }

  const getPopups = computed(() => popupQueue.value)

  return {
    getPopups,
    newPopup,
  }
}

export const popupState = popupStateSetup();
