<script lang="ts">
    import {AppMessage, MsgType, popupStore} from "../stores/PopupStore";
    import {onDestroy} from "svelte";

    let msg: AppMessage;
    const unsubscribe = popupStore.subscribe(value => {
        msg = value;
    })

    onDestroy(unsubscribe)
</script>

{#if msg.msgType !== MsgType.NONE}
    <div class="popup center-fixed">
        <button class="btn-close-sm fixed right-0 top-1" on:click={() => popupStore.close()}>X</button>

        <div class="text-xl font-bold py-0.5">{msg.msgType}</div>
        <div class="text-base">{msg.message}</div>
    </div>
{/if}


<style>
    .popup {
        @apply min-w-[320px] max-w-[70%] min-h-[40px] fixed bottom-20 pt-2 pb-5 px-3
        bg-bg-300 rounded-md shadow-dark-md;
    }

    .center-fixed {
        left: 50%;
        transform: translateX(-50%);
    }
</style>