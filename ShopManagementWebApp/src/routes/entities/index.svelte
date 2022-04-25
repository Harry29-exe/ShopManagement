<script lang="ts">
    import {goto} from "$app/navigation"
    import {page} from "$app/stores"
    import Table from "../../components/table/Table.svelte";
    import {TableHeader} from "../../components/table/TableHeader";
    import {BusinessEntityDTO} from "../../dto/BusinessEntityDTO";
    import {onMount} from "svelte";
    import {BusinessEntityClient} from "../../apiclient/BusinessEntityClient";
    import {popupStore} from "../../stores/PopupStore";

    let tHeader: TableHeader<BusinessEntityDTO> = TableHeader.fromArray([
        ["Id", entity => entity.id],
        ["Name", entity => entity.entityName],
        ["Nip", entity => entity.nip],
        ["Email", entity => entity.email],
        ["Phone", entity => entity.phoneNumber],
    ])
    let entities: BusinessEntityDTO[] = []
    onMount(async () => {
        let result = await BusinessEntityClient.getAll()
        if (result.result) {
            entities = result.result
        } else {
            popupStore.setNew(result.msg)
        }
    })
</script>


<div class="widget-lg w-container-md v-stack px-[5%]">
    <p class="header-lg">Business Entities</p>
    <div class="widget-md mb-8">
        <button class="btn-md"
                on:click={() => goto($page.url + "/new")}>
            +
        </button>
        <button class="btn-md text-black-alpha-500" disabled>
            Search
        </button>
        <button class="btn-md text-black-alpha-500" disabled>
            ?
        </button>
    </div>

    <Table header={tHeader} values={entities}/>

</div>