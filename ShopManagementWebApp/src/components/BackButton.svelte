<script lang="ts">

    const onCloseBtnClick = () => {
        history.back()
    }
</script>


<button on:click={onCloseBtnClick}
        class="btn-back-md absolute top-16 left-4">
    &lt;

</button>