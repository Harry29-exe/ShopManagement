<script lang="ts">
    import {page} from "$app/stores"
    import {goto} from "$app/navigation"

</script>


<div class="widget-lg w-container-md v-stack">

    <p class="header-lg">Select report type</p>

    <div class="h-stack">

        <button on:click={() => goto($page.url + "/sellers-sales")}
            class="card-md card-hover w-[200px] h-[100px]">
            Sellers sales report
        </button>

        <button on:click={() => goto($page.url + "/sales")}
            class="card-md card-hover w-[200px] h-[100px]">
            Sales report
        </button>

    </div>

</div>