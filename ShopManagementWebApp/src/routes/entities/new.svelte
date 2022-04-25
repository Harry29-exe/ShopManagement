<script lang="ts">

    import {BusinessEntityClient, CreateBusinessEntityRequest} from "../../apiclient/BusinessEntityClient";
    import {AppMessage, popupStore} from "../../stores/PopupStore";

    let name: string, nip: string
    let countryCode: string, city: string;

    const onSubmit = async () => {
        let result = await BusinessEntityClient.create(
            new CreateBusinessEntityRequest(
                name, nip, countryCode, city
            )
        )

        if (result.ok) {
            popupStore.setNew(AppMessage.success("Created business entity"))
        } else {
            popupStore.setNew(result.msg)
        }
    }
</script>

<div class="widget-lg w-container-md v-stack md:px-[5%]">
    <p class="header-lg">Add business entity</p>

    <div class="input-wrapper-sm">
        <p>Name</p>
        <input bind:value={name}/>
    </div>

    <div class="input-wrapper-sm">
        <p>Nip</p>
        <input bind:value={nip}/>
    </div>

    <div class="input-wrapper-sm">
        <p>Country Code</p>
        <input bind:value={countryCode}/>
    </div>

    <div class="input-wrapper-sm">
        <p>City</p>
        <input bind:value={city}/>
    </div>


    <button on:click={onSubmit}
            class="btn-success-md mt-6">
        Submit
    </button>
</div>