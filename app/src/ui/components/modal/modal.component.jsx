import "./modal.style.css"

export function ModalComponent({children, isOpen, setIsOpen}) {

    const handleClose = event => {
        event.preventDefault();
    
        if (event.target === event.currentTarget) {
          setIsOpen(false)
        }
    };

    function renderModal() {
        if(isOpen) {
            return (
                <div className="fundo-modal" onClick={handleClose}>
                    {children}
                </div>
            )
        }
    }

    return (
        <>
        {renderModal()}
        </>
    )
}