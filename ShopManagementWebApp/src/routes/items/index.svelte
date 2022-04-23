<script lang="ts">
    import Table from "../../components/table/Table.svelte";
    import {TableHeader} from "../../components/table/TableHeader";
    import {ItemDTO} from "../../dto/ItemDTOs";
    import {goto} from "$app/navigation";
    import {page} from "$app/stores"
    import {onMount} from "svelte";
    import {ItemClient} from "../../apiclient/ItemClient";
    import {popupStore} from "../../stores/PopupStore";

    let tHeader: TableHeader<ItemDTO> = TableHeader.fromArray([
        ["Id", obj => obj.id],
        ["Name", obj => obj.name],
        ["Code name", obj => obj.codeName],
        ["Description", obj => obj.description],
        ["Current Price", obj => obj.currentPrice]
    ], item => goto($page.url + "/" + item.id));

    let items: ItemDTO[] = [];
    onMount(async () => {
        let result = await ItemClient.getAll();
        if (result.result) {
            items = result.result
        } else {
            popupStore.setNew(result.msg)
        }
    })
</script>

<div class="widget-lg w-container-md v-stack px-[5%]">
    <p class="header-lg">Items</p>

    <div class="widget-md mb-8">
        <button class="btn-md">
            Add
        </button>
        <button class="btn-md text-black-alpha-500" disabled>
            Search
        </button>
        <button class="btn-md text-black-alpha-500" disabled>
            ?
        </button>
    </div>

    <Table header={tHeader} values={items}/>

</div>