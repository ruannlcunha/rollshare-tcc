import { useState } from "react";
import { axiosInstance } from "../../base/axios-instance.api";
import { useToast } from "../../toast/use-toast.hook";

export function useFeed() {
    const [conteudos, setConteudos] = useState([])
    const { toastError } = useToast();

    async function _feed(page) {
        const response = await axiosInstance.get(
        `/conteudos/feed?page=${page}`,
        );
        return response.data;
    }

    async function feed(page) {
        try {
            const _conteudos = await _feed(page)
            setConteudos(_conteudos)
        } catch (error) {
            toastError(error)
        }
    }

    return { conteudos, setConteudos, feed };
}
